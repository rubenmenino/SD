machine=sd302@l040101-ws05.ua.pt

echo "Executing program at the Destination Airport node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Server.DestinationAirportMain'
