/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2015, Sandeep Gupta
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

package com.sangupta.andruil.commands.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Display the current IP address of the machine.
 * 
 * @author sangupta
 *
 */
public class MyIPAddress extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "myip";
	}

	@Override
	public String getHelpLine() {
		return "Display the IP address of this machine";
	}

	@Override
	public void execute(String[] arguments) {
		InetAddress address = getIPAddress();
		if(address == null) {
			System.out.println("Unknown");
		} else {
			System.out.println(address.toString().substring(1));
		}
	}
	
	private InetAddress getIPAddress() {
		try {
			InetAddress candidateAddress = null;
	        // Iterate all NICs (network interface cards)...
	        for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // Iterate all IP addresses assigned to each card...
	            for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (!inetAddr.isLoopbackAddress()) {

	                    if (inetAddr.isSiteLocalAddress()) {
	                        // Found non-loopback site-local address. Return it immediately...
	                        return inetAddr;
	                    }
	                    else if (candidateAddress == null) {
	                        // Found non-loopback address, but not necessarily site-local.
	                        // Store it as a candidate to be returned if site-local address is not subsequently found...
	                        candidateAddress = inetAddr;
	                        // Note that we don't repeatedly assign non-loopback non-site-local addresses as candidates,
	                        // only the first. For subsequent iterations, candidate will be non-null.
	                    }
	                }
	            }
	        }
	        
	        if (candidateAddress != null) {
	            // We did not find a site-local address, but we found some other non-loopback address.
	            // Server might have a non-site-local address assigned to its NIC (or it might be running
	            // IPv6 which deprecates the "site-local" concept).
	            // Return this non-loopback candidate address...
	            return candidateAddress;
	        }
	        // At this point, we did not find a non-loopback address.
	        // Fall back to returning whatever InetAddress.getLocalHost() returns...
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        if (jdkSuppliedAddress == null) {
	        	System.out.println("Unable to find the IP address of the machine: The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
	        }
	        
	        return jdkSuppliedAddress;
		} catch (SocketException e) {
			System.out.println("Unable to find the IP address of the machine: " + e.getMessage());
		} catch (UnknownHostException e) {
			System.out.println("Unable to find the IP address of the machine: " + e.getMessage());
		}
		
		return null; 
	}

}