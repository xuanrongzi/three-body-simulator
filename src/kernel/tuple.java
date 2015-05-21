package kernel;

import java.util.Hashtable;

//tuple is a tool that I can easily map one thing to another
public class tuple<K,V> {
	K key;
	V value;
	Hashtable<K, V> tupletalbe;
	
	public tuple(){
		tupletalbe=new Hashtable<K, V>(16);
	}
	

	
	public void add(K k, V v){
		if (!tupletalbe.containsKey(k)){
			tupletalbe.put(k, v);
		}else{
			tupletalbe.replace(k, v);
		}
	}
}
