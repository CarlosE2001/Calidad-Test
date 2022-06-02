public  class Curso{
	private String nombre;
	private String[] dias = new String[2];
	private double[] horario = new double[2]; 
	public Curso(String nombre, String diaUno, String diaDos, double inicia, double termina){
		this.nombre = nombre;
		this.dias[0] = diaUno;
		this.dias[1] = diaDos;
		this.horario[0] = inicia;
		this.horario[1] = termina;
	}


}