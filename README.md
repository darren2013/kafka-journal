# kafka journal


---


#####此代码是从graylog2项目中分离出来，可以使用此作为缓存的本地持久化存储，以便利用kafka高性能

###写性能测试:
        
content length 246 in bytes  
run 0 eps=315,357  
run 1 eps=423,549  
run 2 eps=415,973  
run 3 eps=435,540  
run 4 eps=459,558  
run 5 eps=468,384  
run 6 eps=459,136  

content length 684 in bytes  
run 0 eps=147,318  
run 1 eps=179,147  
run 2 eps=183,688  
run 3 eps=183,049  
run 4 eps=184,128  
run 5 eps=181,521  
run 6 eps=185,356

content length 1230 in bytes
run 0 eps=114,194  
run 1 eps=108,506   
run 2 eps=106,055  
run 3 eps=105,152  
run 4 eps=110,035  
run 5 eps=108,577  
run 6 eps=106,123  

content length 2214 in bytes               
run 0 eps=55,688  
run 1 eps=58,906  
run 2 eps=58,146  
run 3 eps=55,570  
run 4 eps=54,764  
run 5 eps=55,047  
run 6 eps=52,573  

###读的性能测试：  

content length 246 in bytes  
run 0 eps=123,107  
run 1 eps=151,469  
run 2 eps=148,279  
run 3 eps=149,253  
run 4 eps=154,894  
run 5 eps=153,045  
run 6 eps=202,922  

content length 2214 in bytes  
run 0 eps=30,023  
run 1 eps=32,085  
run 2 eps=33,324  
run 3 eps=30,770  
run 4 eps=32,846  
run 5 eps=32,913  
run 6 eps=34,294  

读写性能和内容长度紧密相关，目前只做了简单测试