import static java.lang.System.*;

public final class Demo{
    public static void main(String[] args) {

    	int i;
    	int sum;
    	System.out.printf("%s", "Hello Yuyang");
    	for ( i = 1 ; i < 10; i++)
    		System.out.printf("%d ", i );
    	
    	
    	sum= 0;
    	for ( i= 1; i<= 100; i++)
    		sum = sum + i;
    	System.out.printf("\n%d", sum);
    	
    }
}

