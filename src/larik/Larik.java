package larik;

/**
 * @author Yusuf Aji Wibowo
 *
 */
public class Larik {
	private int size;
	private int item[];
	
	public Larik() {
		
	}
	public Larik(int n){
		this.size = n;
		item = new int[n];
	}
	
	public void setItem(int id, int dt){
			this.item[id] = dt;
	}
	
	public int getItem(int id){
		int x = item[id];
		return x;
	}
	
	public int getSize(){
		int n = this.size;
		return n;
	}
	
	public int getPos(int dt){
		int dataCari = dt;
		int k = 0;
		for(int i =0; i<this.size;i++){
			 if(item[i]==dataCari) {
			}
			 if(false){
					k = i;
				}
			 else
				 k = Integer.parseInt("kosong");
		}
		return k;
	}
	
	public int getMax(){
		int max=item[0];
		for(int i=1;i<this.size;i++){
			while((Integer)item[i] > (Integer) max){
				max=item[i];
			}
		}
		return max;
	}
	
	public int getMin(){
		int min = item[0];
		for(int i=1;i<this.size;i++){
			while((Integer)item[i] < (Integer) min){
				min=item[i];
			}
		}
		return min;
	}
	
	public Larik kali(Larik A, Larik B){
		Larik l = null;
		if(A.getSize() == B.getSize()){
			l = new Larik(A.getSize());
			for (int i = 0; i < A.size; i++) {
				l.setItem(i, A.getItem(i) * B.getItem(i));
			}
		}
		return l;
	}
	
	public Larik sort(){
		Larik hasil;
		hasil = new Larik(this.size);
		int urut;
		for (int i = 1; i < this.size; i++) {
				if(this.item[i] < (this.item[i-1])){
					urut = item[i-1];
					this.item[i-1]=this.item[i];
					this.item[i]= urut;
					}
				}
		for (int i = 0; i < this.size; i++) {
			hasil.setItem(i, item[i]);
		}
		return hasil;
	}
	public Larik copy(int id, int n){
		Larik hasil = null;
		if(id < this.size && (n < this.size)){
			hasil = new Larik(n);
			int k =0 ;
			for (int i = id; i < hasil.size; i++) {
				hasil.setItem(k, this.item[i]);
				k++;
			}
		}
		return hasil;
	}
}
