machine=sd302@l040101-ws03.ua.pt

echo "Executing program at the Passenger node."
sshpass -f password ssh $machine 'cd test/AirLift/ ; java -cp .:./genclass.jar Client.PassengerMain'
