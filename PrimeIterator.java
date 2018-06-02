/*---------------------------------------
 Genuine author: <Nitzan Cohen>, I.D.: <203980750>
 Date: 05-01-2018
---------------------------------------*/

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PrimeIterator implements Iterator<Integer> {

    public List<Integer> primes;
   
	//Complete the following method
    public PrimeIterator(){
        primes=new LinkedList<>();
        primes.add(2);
    }

	//Complete the following method
    public boolean hasNext(){
        
    	return (primes.size()>0);
    }

	//Complete the following method
    public Integer next(){
        Integer nextNum=primes.get(0);
        while(!isPrime(nextNum)){
            nextNum++;
        }
        primes.remove(0);
        primes.add(nextNum+1);

        return nextNum;
    }
    private static boolean isPrime(int num){
        // if the number is multiplication of 2 then there is no need to check the rest
        if(num==2)
            return true;
        else if(num%2==0)
            return false;
        // it is enough to check until and include the number square root
        // instead of using square root function, i'll use the index square.
        for(int i=3;i*i<=num;i=i+2)
            if(num%i==0)
                return false;
        return true;
    }
	//DO NOT REMOVE OR CHANGE THIS MEHTOD – IT IS REQUIRED 
	public void remove() {
		return;
	}


}
