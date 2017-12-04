rm -rf nohup.out
nohup java -classpath '.:resources' -Xms512m -Xmx2048M cn.rebind.Entry & 
