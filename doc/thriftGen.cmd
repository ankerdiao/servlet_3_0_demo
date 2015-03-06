@echo Start Generate Thrift Codes
 
thrift-0.9.2.exe -version


thrift-0.9.2.exe -out ../src --gen java ../src/com/qq/servlet/demo/netty/sample/thrift/Idgen.thrif



pause 

@echo End Generatation. Veery Good!