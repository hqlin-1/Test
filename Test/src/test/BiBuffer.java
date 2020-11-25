package test;

public class BiBuffer {
	private Object bufs[];
    private byte rwstat[];
    private int bufToRead, bufToWrite;
    private int readingReaders;
    
    public static final byte READING = 0;
    public static final byte WRITTEN = 1;
    public static final byte READDONE = 2;
    public static final byte WRITING = 3;
    
    public Object[] initdata(Object[] data) {
     //子类可重写之
    	
     return data;
    }
    
    public Object copydata(Object src) {
     //子类可重写之
     return src;
    }
    
    public void reset() {
       rwstat[0]=READDONE; rwstat[1]=READDONE;
       readingReaders=0;
       bufToWrite=0; bufToRead=0; 
    }
    
    public BiBuffer(Object[] data) {
       rwstat=new byte[2];
       reset();
       bufs=initdata(data);
    }
	//……
    public synchronized Object openBuf(char request) {
        //必须用synchronized关键字，以实现对状态变量rwstat读写的互斥
        if (request=='w') {
          //若请求的是写操作
            try {
            while (rwstat[bufToWrite]!=READDONE) {
              //阻塞直至bufToWrite号缓冲可写
                wait();
            }
            }catch(InterruptedException inte){}
            rwstat[bufToWrite]=WRITING;//把状态置为在写
            return bufs[bufToWrite];//返回该块缓冲对象
        }
        else if (request=='r') {
            try {
            while (rwstat[bufToRead]>=READDONE) {
              //阻塞直至bufToRead号缓冲可读
                wait();
            }
            }catch(InterruptedException inte){}
            rwstat[bufToRead]=READING;//把状态置为在读
            readingReaders++;//读者数加1(因为可以被不止一个对象读)
            return copydata(bufs[bufToRead]);//返回该块缓冲对象的拷贝
        }
        else return null;        
    }   
    //……
    
    public synchronized boolean closeBuf(Object buf) {
        int bufid;
        if (bufs[0]==buf) bufid=0;
        else if (bufs[1]==buf) bufid=1;
        else return false;     
        try {
          if (rwstat[bufid]==WRITING) {
              //若该块缓冲是被写的
              rwstat[bufid]=WRITTEN;//设状态为可读
              bufToWrite=1-bufToWrite;//当前应写的缓冲切换到另一个
              return true;
          }
          else if (rwstat[bufid]==READING) {
              //若该块缓冲是被读的
              if ((--readingReaders)==0) rwstat[bufid]=READDONE;//递减读者数量。若减到0则置状态为可写
              bufToRead=1-bufToRead;//当前应读的缓冲切换到另一个
              return true;
          }
          else return false;
          }finally {
              notifyAll();//唤醒所有因条件不满足而等待的线程
          }
          
      }
}

