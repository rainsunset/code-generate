# code-generate
**链接测试**
```$xslt
http://localhost:8180/code-generate/generate/test
{
	"dbType":"MySql",
	"dbName":"wtgldb2",
	"address":"localhost",
	"port":"3306",
	"username":"root",
	"password":"root"
}
```

**查询数据库表**
```$xslt
http://localhost:8180/code-generate/generate/getTabs
{
	"dbType":"MySql",
	"dbName":"wtgldb2",
	"address":"localhost",
	"port":"3306",
	"username":"root",
	"password":"root"
}
```

**生成代码**
```$xslt
http://localhost:8180/code-generate/generate/execute
{
	"dataBaseConfigReqDTO":{
		"dbType":"MySql",
		"dbName":"wtgldb2",
		"address":"localhost",
		"port":"3306",
		"username":"root",
		"password":"root"
	},
	"filePathConfigReqDTO":{
		"baseClassPath":"com.cmbi",
		"moduleName":"information",
		"outFilePath":"/home/ligw/code",
		"generateModel":true,
		"generateDao":true,
		"generateService":true,
		"generateController":true,
		"generateTest":true,
		"generatePage":true
	},
	"templatesType":"cmbi",
	"tabNameList":["act_ge_bytearray","act_ru_task","t_accident_responsibility_investigate_notification_chat_log",
	"t_address_and_map","t_safety_work_supervise_department"]
}
```

