/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/andruil
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.andruil.completer;

import java.io.File;
import java.util.List;

import jline.console.completer.FileNameCompleter;
import jline.internal.Configuration;

/**
 * Extension to the original FileNameCompleter that allows to extend
 * filenames that are part of the original command
 * 
 * @author sangupta
 *
 */
public class ExtendedFileNameCompleter extends FileNameCompleter {

	private static final boolean OS_IS_WINDOWS;

    static {
        String os = Configuration.getOsName();
        OS_IS_WINDOWS = os.contains("windows");
    }

    public int complete(String buffer, final int cursor, final List<CharSequence> candidates) {
        // buffer can be null
        assert candidates != null;

        if (buffer == null) {
            buffer = "";
        }
        
        int index = buffer.lastIndexOf(' ');
        String preCommand = "";
        if(index > -1) {
        	preCommand = buffer.substring(0, index + 1);
        	buffer = buffer.substring(index + 1);
        }

        if (OS_IS_WINDOWS) {
            buffer = buffer.replace('/', '\\');
        }

        String translated = buffer;

        File homeDir = getUserHome();

        // Special character: ~ maps to the user's home directory
        if (translated.startsWith("~" + separator())) {
            translated = homeDir.getPath() + translated.substring(1);
        }
        else if (translated.startsWith("~")) {
            translated = homeDir.getParentFile().getAbsolutePath();
        }
        else if (!(translated.startsWith(separator()))) {
            String cwd = getUserDir().getAbsolutePath();
            translated = cwd + separator() + translated;
        }

        File file = new File(translated);
        final File dir;

        if (translated.endsWith(separator())) {
            dir = file;
        }
        else {
            dir = file.getParentFile();
        }

        File[] entries = dir == null ? new File[0] : dir.listFiles();

        int value = matchFiles(buffer, translated, entries, candidates);
        for(int iter = 0; iter < candidates.size(); iter++) {
        	CharSequence seq = candidates.get(iter);
        	seq = preCommand + seq;
        	candidates.set(iter, seq);
        }
        
        return value;
    }
}
