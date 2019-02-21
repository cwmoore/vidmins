#!/usr/bin/env bash

# name of this app
APPNAME="vidmins"
PROPDIR="/etc/$APPNAME"

# set a location for credentials off of the dev tree
if [ ! -d "$PROPDIR" ]; then
    mkdir $PROPDIR
fi

# get user input for database credentials
echo "Creating database.properties file:"

echo "DB Username:"
read USERNAME

echo "DB Password:"
read -s PASSWORD

# insert the values into a database.properties template
read -r -d '' PROPERTIES << EndOfDoc
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/vidmins?useSSL=false
user=$USERNAME
pass=$PASSWORD
EndOfDoc

echo "$PROPERTIES" > "$PROPDIR/database.properties"
