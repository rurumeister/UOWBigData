# HBase Notes:
## HBase(Hadoop Database) Data Model:
- open source distributed db based on data model of Google's BigTable
- provides BigTable view of data stored in HDFS
- data model is sparce, distributed, persistent multidimensional sorted map
- indexed by row key, column key and timestamps
- organizes data into tables, HBase table consists of rows and each row is uniquely identified by a row key
- data in a row is grouped by a column family. Column families have an important impact on the physical implementation of HBase table.
- combination of row key, column family and column qualifier uniquely identifies a cell
- values in cells do not have a data type and are always treated as a sequence of bytes

##Logical view of data:
- values in cell have multiple versions (identified by version number, default is a timestamp of when they were inserted into the table)
- if no time stamp is determined at write time, curr timestamp will be used, if no timestamp is determined during read, the latest is determined
- max allowed number of cell value versions is determined for column family. Default number of versions is three
HBase table as a nested struct:
```
{"Row-0001":
	{"Home":
		{"Name":	
			{"timestamp-1":"James"}
		"Phones":
			{"timestamp-2":"2 42 214339"
			"timestamp-2":"2 42 213456"
			"timestamp-3":"+61 2 4567890"}
		}
	"Office":
		{"Phone":
			{"timestamp-4":"+64 345678"}
		"Address":
			{"timestamp-5":"10 Ellenborough Pl"}
		}
}
{"Row-0002":
	{"Home":
		{"Name":	
			{"timestamp-6":"Harry"}
		"Phones":
			{"timestamp-7":"2 42 214339"}
		}
	"Office":
		{"Phone":
			{"timestamp-8":"+64 345678"}
		"Address":
			{"timestamp-9":"10 Ellenborough Pl"
			"timestamp-10"23 Victoria Rd"}
		}
}
```
HBase table as a key -> value store:
"Row-001"-> "Home":{"Name":{"timestamp-1":"James"}
HBase Notes:
## HBase(Hadoop Database) Data Model:
- open source distributed db based on data model of Google's BigTable
- provides BigTable view of data stored in HDFS
- data model is sparce, distributed, persistent multidimensional sorted map
- indexed by row key, column key and timestamp
- organizes data into tables, HBase table consists of rows and each row is uniquely identified by a row key
- data in a row is grouped by a column family. Column families have an important impact on the physical implementation of HBase table.
- combination of row key, column family and column qualifier uniquely identifies a cell
- values in cells do not have a data type and are always treated as a sequence of bytes

## Logical view of data:
- values in cell have multiple versions (identified by version number, default is a timestamp of when they were inserted into the table)
- if no time stamp is determined at write time, curr timestamp will be used, if no timestamp is determined during read, the latest is determined
- max allowed number of cell value versions is determined for column family. Default number of versions is three
HBase Table as a nested struct:

```
{"Row-0001":
	{"Home":
		{"Name":	
			{"timestamp-1":"James"}
		"Phones":
			{"timestamp-2":"2 42 214339"
			"timestamp-2":"2 42 213456"
			"timestamp-3":"+61 2 4567890"}
		}
	"Office":
		{"Phone":
			{"timestamp-4":"+64 345678"}
		"Address":
			{"timestamp-5":"10 Ellenborough Pl"}
		}
}
{"Row-0002":
	{"Home":
		{"Name":	
			{"timestamp-6":"Harry"}
		"Phones":
			{"timestamp-7":"2 42 214339"}
		}
	"Office":
		{"Phone":
			{"timestamp-8":"+64 345678"}
		"Address":
			{"timestamp-9":"10 Ellenborough Pl"
			"timestamp-10"23 Victoria Rd"}
		}
}
HBase table as a key -> value store
"Row-001"-> {"Home":{"Name":{"timestamp-1":"James"}
                            "Phones":{"timestamp-2":"2 42 214339"
                                      "timestamp-3":"2 42 213456"
                                      "timestamp-4":"+61 2 4567890"}
                            }
                    "Office":{"Phone":{"timestamp-5":"+64 345678"}
                    "Address":{"timestamp-6":"10 Ellenborough Pl"}
                            }
                    }
"Row-0001" "Home"->{"Name":{"timestamp-1":"James"}
                            "Phones":{"timestamp-2":"2 42 214339"
                                    "timestamp-3":"2 42213456"
                                    "timestamp-4":"+61 2 4567890"}
                            }
```
- A key can be a row key or combination of a row key, column family, qualifier and timestamp depending on whats
supposed to be retrieved
- if cells in a row are of interest then a key is a row key
- for specific cells, appropriate column families and qualifiers are part of a key

## Design fundamentals:
Considerations when designing Hbase table
- what should be a row key and what should it contain? how many column families, what columns(qualifiers) should be included in each column family?
- what information should go into the cells? how many versions stored for each cell?
4 Level Hierarchical Strucutre:
1. table consists of rows
2. rows consists of column families
3. column families consist of columns
4. columns consists of versions.
* if cells contain the keys then HBase table becomes a network/ graph structure.
Facts to remember:
- Indexing is performed only for a row key
- Hbase tablels are stoerd and sorted based on a row key
- Everything in Hbase tables is stored as untyped sequence of bytes
- Atomicity is guaranteed only at row level and there are no multi-row transactions
- Column families must be defined at Hbase table creation time
- Column qualifiers are dynamic and can be defined at write time
- Column qualifiers are stored as sequences of bytes such that they can represent data

Implementation of Entity type?
{"007":
    {"CUSTOMER":
        {}}}