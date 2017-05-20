#!/bin/bash

dirs=(ApiGateway AuthService ItemManagementService ShopService ShippingService TurbineHystrixDashboard)

let i=1

for dir in "${dirs[@]}"; do
    echo "Building $dir"
    (cd $dir; mvn clean package docker:build -DskipTests) > build.out
    SUCCESS=$?
    if [ $SUCCESS -eq 0 ]; then
      echo "$dir was built successfully!"
    else
      echo "Error occured while building $dir, aborting!"
      exit 1
    fi

done

echo "All services and docker images were build successfully!"
###(cd ShippingService; mvn clean package docker:build -DskipTests) &