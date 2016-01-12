# kafka journal


---


#####此代码是从graylog2项目中分离出来，可以使用此作为缓存的本地持久化存储，以便利用kafka高性能特性

下面测试基于macMF840,i7-2.4GHZ处理器,8G内存，SSD硬盘，后面有基于centos机械硬盘的测试

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


centos6.5，i-7-3.4GHZ,16g内存，1T机械硬盘测试  
content length 246 in bytes  
run 0 eps=609,384  
run 1 eps=662,251  
run 2 eps=682,128  
run 3 eps=687,285  
run 4 eps=690,131  
run 5 eps=671,591  
run 6 eps=686,341  
content length 684 in bytes  
run 0 eps=260,824  
run 1 eps=259,942  
run 2 eps=258,799  
run 3 eps=256,016  
run 4 eps=219,346  
run 5 eps=100,080  
run 6 eps=94,759  
content length 1230 in bytes  
run 0 eps=65,248  
run 1 eps=64,312  
run 2 eps=60,690  
run 3 eps=58,661  
run 4 eps=57,653  
run 5 eps=59,577  
run 6 eps=64,057  
content length 2214 in bytes  
run 0 eps=34,191  
run 1 eps=34,993  
run 2 eps=32,820  
run 3 eps=33,527  
run 4 eps=33,070  
run 5 eps=34,211  
run 6 eps=34,280  