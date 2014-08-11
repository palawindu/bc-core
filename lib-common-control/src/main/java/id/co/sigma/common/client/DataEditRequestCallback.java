package id.co.sigma.common.client;

/**
 * ini callback, ini akan di kembalikan ke caller kalau view, edit add sudah selesai di lakukan
 * @author <a href="mailto:gede.sutarsa@gmail.com">Gede Sutarsa</a>
 */
public interface DataEditRequestCallback<DATA> {
	
	
	
	/**
	 * handler kalau data sudah selesai di kerjakan
	 */
	public void onEditComplete (DATA afterEditedData) ;

}
