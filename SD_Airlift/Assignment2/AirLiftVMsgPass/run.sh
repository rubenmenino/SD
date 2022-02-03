#xterm  -T "General Repository" -hold -e "./RepositoryDeployAndRun.sh" &
#xterm  -T "Departure Airport" -hold -e "./DepartureAirportDeployAndRun.sh" &
#xterm  -T "Destination Airport" -hold -e "./DestinationAirportDeployAndRun.sh" &
#xterm  -T "Plane" -hold -e "./PlaneDeployAndRun.sh" &
#sleep 1
#xterm  -T "Pilot" -hold -e "./PilotDeployAndRun.sh" &
#xterm  -T "Hostess" -hold -e "./HostessDeployAndRun.sh" &
#xterm  -T "Passengers" -hold -e "./PassengersDeployAndRun.sh" &


xterm  -T "General Repository" -hold -e "./run/RepositoryDeployAndRun.sh" &
xterm  -T "Departure Airport" -hold -e "./run/DepartureAirportDeployAndRun.sh" &
xterm  -T "Destination Airport" -hold -e "./run/DestinationAirportDeployAndRun.sh" &
xterm  -T "Plane" -hold -e "./run/PlaneDeployAndRun.sh" &
sleep 1
xterm  -T "Pilot" -hold -e "./run/PilotDeployAndRun.sh" &
xterm  -T "Hostess" -hold -e "./run/HostessDeployAndRun.sh" &
xterm  -T "Passengers" -hold -e "./run/PassengersDeployAndRun.sh" &