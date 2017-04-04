package lab02;

public class ContrivedExample {
	
	String example;
	
	public static void main(String[] args) {
		ContrivedExample example = new ContrivedExample();
		
		example.method1().method2().method3().method4().method6();
		
		example.methodA();
		
		System.out.println(example.toString());
	}
	
	public ContrivedExample method1() {
		example += "Method 1 has been called\n";
		return this;
	}
	
	public ContrivedExample method2() {
		example += "I can count to: ";
		for(int idx = 0; idx < 10; idx++) {
			example += idx + " ";
		}
		return this;
	}
	
	public ContrivedExample method3() {
		example += "Method 3 has been called\n";
		return this;
	}
	public ContrivedExample method4() {
		example += "Method 4 has been called\n";
		return this;
	}
	public ContrivedExample method5() {
		example = "I'm the special one!";
		return this;
	}
	
	public ContrivedExample method6() {
		example += "Method 6 has been called\n";
		return this;
	}
	
	public void methodA() {
		methodB();
	}
	
	public void methodB() {
		methodC();
	
	}
	
	private void methodC() {
		methodD();
	}

	private void methodD() {
		while(true) {
			//yeah this isn't supspicious at all. 
		}
	}
	
	public String toString() {
		return example;
	}

}
