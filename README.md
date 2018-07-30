# VNFmigration_DC
Trigger based VNF migration in data center
Objective : Model a given IP based network in a data-center with appropriate VNFs
and use YANG to model trigger based migration of VNFs to a different host.
Created fat tree toplology with ovsk switch and pushed rules to it using OpenDayLight Controller (Boron SR3)
Simulated HTTP Server as VNF along with a watcher process which can migrate the server across mininet node and used httperf as a load generator to simulate traffic flow
