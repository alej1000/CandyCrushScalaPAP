package conexionDeScala

object StringToJsonParser{
        def parse(jsonString:String):List[(String,String)]={
        if(jsonString.startsWith("[")&&jsonString.endsWith("]")){
        parseList(jsonString.drop(1).dropRight(1))
        }else{
        throw new IllegalArgumentException("Invalid JSON: not a list")
        }
        }


private def parseList(jsonString:String,acc:List[(String,String)]=Nil):List[(String,String)]={
        if(jsonString.isEmpty){
        acc.reverse
        }else{
        val(head,tail)=parseListItem(jsonString)
        parseList(tail,head::acc)
        }
        }

private def parseListItem(jsonString:String):((String,String),String)={
        val(key,valueString)=parseItem(jsonString.dropWhile(_.isWhitespace))
        val value=parseValue(valueString.dropWhile(_.isWhitespace))
        ((key,value),valueString.dropWhile(_.isWhitespace))
        }

private def parseItem(jsonString:String):(String,String)={
        val keyEndIndex=jsonString.indexOf(':')
        if(keyEndIndex==-1){
        throw new IllegalArgumentException("Invalid JSON item: missing colon separator")
        }
        val key=jsonString.take(keyEndIndex).replaceAll("\"","")
        val valueString=jsonString.drop(keyEndIndex+1)
        (key,valueString)
        }

private def parseValue(jsonString:String):String={
        if(jsonString.startsWith("\"")&&jsonString.endsWith("\"")){
        jsonString.drop(1).dropRight(1)
        }else{
        throw new IllegalArgumentException("Invalid JSON value: not a string")
        }
        }
        }
