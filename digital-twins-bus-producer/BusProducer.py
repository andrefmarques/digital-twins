from time import sleep
from json import dumps
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers=['192.168.160.103:9093'],
                         value_serializer=lambda x: dumps(x).encode('utf-8'))

sleep(100)
with open('bussource.csv', 'r') as bus_csv:
    bus_csv.readline()
    for i in range(0, 100):
        row = bus_csv.readline()
        split = row.split(',')

        info = {
            "id": i,
            "busId": split[1],
            "locationId": split[2],
            "heading": split[3],
            "longitude": split[4],
            "latitude": split[5],
            "speed": split[6],
            "ts": split[7],
            "writeTime": split[8][:-1],
        }

        producer.send('esp41-buses', value=info)
        sleep(30)
