
# FastestPath - Server [![Circle CI](https://circleci.com/gh/FastestPath/server/tree/master.svg?style=svg&circle-token=ab3aecc444dcadadac13a3f5d1f7fb814bcf0a89)](https://circleci.com/gh/FastestPath/server/tree/master)
Fetches, parses and serves the latest schedules.

## Getting Started
To start the development server:

```
cd server/
./gradlew run
```

## Fetching Schedule Information
To fetch a schedule, follow the template below:

```
GET api/schedule?from=WORLD_TRADE_CENTER&to=JOURNAL_SQUARE&departAt=2016-10-29T23:03:27.845Z
```

Note: the `departAt` parameter is optional. It will assume the current time if omitted.

Here is the list of valid `from` and `to` parameters.
```
HOBOKEN
CHRISTOPHER_STREET
NINTH_STREET
FOURTEENTH_STREET
TWENTY_THIRD_STREET
THIRTY_THIRD_STREET
NEWPORT
EXCHANGE_PLACE
NEWARK
JOURNAL_SQUARE
GROVE_STREET
WORLD_TRADE_CENTER
HARRISON
```

## Deployment Notes

Create a cluster using the gcloud dashboard.

Create a persistent disk.
```
gcloud compute disks create --size 50GB fastestpath-disk
```

Use gcloud to pull down kubernetes credentials for a cluster.
```
gcloud container clusters get-credentials cluster-1
```

Start the kubernetes dashboard
```
kubectl proxy
```

Go to `localhost:8001/ui` to view any existing deployments.

Use kubernetes to create a persistent volume and volume claim.
```
kubectl create -f persistent-volume.yml 
kubectl create -f persistent-volume-claim.yml 
```

Use kubernetes to create a deployment.
```
kubectl create -f deployment.yml
```

Expose the deployment.
```
kubectl expose deployment fastestpath-deployment --type="LoadBalancer" --port=80 --target-port=9000
```