#!/usr/bin/python

import subprocess
import time
import signal
import sys
import os

# Check for command line args
if (len(sys.argv) < 2):
    print "Usage: " + sys.argv[0] + " <server_no>"

this_server = int(sys.argv[1])
this_server_running = False
server_process = None

web_server_cmd = "python -m SimpleHTTPServer"

# Handler for Ctrl C SIGNINT
def signal_handler(singal, frame):
    print('Existing Server')
    sys.exit(0)

def start_server():
    p = subprocess.Popen(web_server_cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
            shell=True, preexec_fn=os.setsid) 
    print "Started Web Server..."
    return p

def stop_server(p):
    p.terminate()
    print "Stopping Server"


# Register the SIGINT handler
signal.signal(signal.SIGINT, signal_handler)

# Read from the server file

while True:
    server_file = open("server_file")
    current_server = int(server_file.read())
    server_file.close()

    if current_server == this_server and this_server_running != True:
        server_process = start_server()
        this_server_running = True;
    elif current_server != this_server and this_server_running == True:
        stop_server(server_process)
        this_server_running = False;

    time.sleep(1)


#p = subprocess.Popen(web_server_cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE,
#        shell=True, preexec_fn=os.setsid) 
#print "Started Web Server..."
#
## Now poll if the server needs to be stopped
#while p.poll()==None:
#    time.sleep(1)
#
