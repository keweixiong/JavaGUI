package objectoriented;

// half way

abstract  class AbstractStudent{
    abstract void study(); 
     void sleep(){ 
             System.out.println("sleep"); 
      }

}
//��ͨ��ѧ�����̳�AbstractStudent����ѧϰ��˯���ķ�����������
class Student extends AbstractStudent{
	void study() {
		System.out.println("study");
	}
}


interface Smoking{
    void smoke();
}


//���̵�ѧ�������˼̳�AbstractStudent�еķ������������
class SmokeStudent  implements Smoking{
	
	void smoke() {
		system.out.println("smoking");
	}
	public statid void main( string args[]) {
		Student  s = new SmokeStudent ();
		s.smoke();
	}
	}
}
