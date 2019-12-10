package objectoriented;

// half way

abstract  class AbstractStudent{
    abstract void study(); 
     void sleep(){ 
             System.out.println("sleep"); 
      }

}
//普通的学生，继承AbstractStudent，有学习、睡觉的方法，不抽烟
class Student extends AbstractStudent{
	void study() {
		System.out.println("study");
	}
}


interface Smoking{
    void smoke();
}


//抽烟的学生，除了继承AbstractStudent中的方法，还会抽烟
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
