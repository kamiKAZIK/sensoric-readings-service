CREATE KEYSPACE IF NOT EXISTS sensoric
  WITH REPLICATION = {
   'class' : 'SimpleStrategy',
   'replication_factor' : 1
  };

CREATE TABLE sensoric.dht_readings (
   sensor_id UUID,
   write_date DATE,
   write_time TIME,
   temperature FLOAT,
   humidity FLOAT,
   PRIMARY KEY ((sensor_id, write_date), write_time)
)
WITH CLUSTERING ORDER BY (write_time DESC) AND
default_time_to_live = 2592000;