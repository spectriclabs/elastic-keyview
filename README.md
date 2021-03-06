Introduction
============

ElasticSearch has the ability to store secure settings in a
[KeyStore](https://www.elastic.co/guide/en/elasticsearch/reference/current/elasticsearch-keystore.html). 

This makes debugging configuration issues that use the keystore incredibly difficult.  Is something not working because of an invalid value in the keystore
or is it not working because of some other mis-configuration.   By using the `elastic-keystore` tool you can create and edit values in the KeyStore, but the `list` command only shows the setting names and does not display the values.

Presumably this was done as a "security" feature, but it is merely security
through obscurity.  The _actual_ security of the keystore comes from two sources:
(a) access control mechanisms on the file and (b) encrypting the keystore with a
password.  If an adversary has access to the keystore file and knows the password,
then reading the secure values is trivial.

This simple tool list you view the values in the keystore (assuming you know the password).  If you do not know the password, this will not help you.

Usage
=====

Prerequisites:
* Have gradle version 4.9+

```
git clone https://github.com/spectriclabs/elastic-keyview
gradle build
gradle run --args="/usr/share/elasticsearch/config"
```


