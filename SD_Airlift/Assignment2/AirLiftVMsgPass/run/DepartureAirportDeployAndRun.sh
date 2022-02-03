machine=sd302@l040101-ws04.ua.pt

echo "Executing program at the Departure Airport node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Server.DepartureAirportMain'
