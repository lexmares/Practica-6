package ed.ito;

import funcionamiento.*;

import java.util.Comparator;

public class ArbolBinario<E> implements Arbol<E> {

	private NodoArbol<E> raiz;
	
	
	public ArbolBinario() {
		raiz=null;
	}
	
	private NodoArbol<E> add(NodoArbol<E> r,E item) throws ExcepcionNodoRepetido {
		if(r==null)
			return new NodoArbol<E>(item);
		@SuppressWarnings("unchecked")
		Comparable<E> c=(Comparable<E>)item;
		if(c.compareTo(r.getItem())==0)
			throw new ExcepcionNodoRepetido("El item ya se localiza en el árbol");
		if(c.compareTo(r.getItem())<0)
			r.setIzquierdo(add(r.getIzquierdo(),item));
		else
			r.setDerecho(add(r.getDerecho(),item));
		return r;
	}
	
	@Override
	public void add(E item) throws ExcepcionNodoRepetido {
		// TODO Auto-generated method stub
		this.raiz=this.add(this.raiz,item);	
	}
	
	private NodoArbol<E> delete(NodoArbol<E> r,E item) throws ExcepcionNodoNoLocalizado{
		@SuppressWarnings("unchecked")
		Comparable<E> c=(Comparable<E>) item;
		NodoArbol<E> retorno=r;
		if(r==null)
			throw new ExcepcionNodoNoLocalizado("Item no encontrado para eliminar!!");
		if(c.compareTo(r.getItem())==0) {
		    if(r.getIzquierdo()==null && r.getDerecho()==null)
		    	retorno=null;
		    else if(r.getIzquierdo()==null)
		    	retorno=r.getDerecho();
		    else {
			    retorno=r.getIzquierdo();
			    if(r.getDerecho()!=null) {
			    	NodoArbol<E> aux=retorno;
			    	while(aux.getDerecho()!=null)
			    		aux=aux.getDerecho();
			    	aux.setDerecho(r.getDerecho());
			    }
		    }
		}else if(c.compareTo(r.getItem())<0)
			r.setIzquierdo(this.delete(r.getIzquierdo(),item));
		else
			r.setDerecho(this.delete(r.getDerecho(), item));
		
		return retorno;
	}

	@Override
	public void delete(E item) throws ExcepcionNodoNoLocalizado {
		// TODO Auto-generated method stub
		if(this.isFree())
			throw new ExcepcionNodoNoLocalizado("El arbol esta vacio");
		this.raiz=this.delete(this.raiz,item);
	}

	@Override
	public boolean isFree() {
		// TODO Auto-generated method stub
		return this.raiz==null;
	}
	
	private int lengthHojas(NodoArbol<E> r) {
		if(r==null)
			return 0;
		if(r.getIzquierdo()==null && r.getDerecho()==null)
			return 1;
		return lengthHojas(r.getIzquierdo())+lengthHojas(r.getDerecho());
	}

	@Override
	public int lengthHojas() {
		// TODO Auto-generated method stub
		return lengthHojas(this.raiz);
	}

	private int getAltura(NodoArbol<E> r) {
		if(r==null)
			return 0;
		int izq=getAltura(r.getIzquierdo());
		int der=getAltura(r.getDerecho());
		return 1+(izq>der?izq:der);
	}
	@Override
	public int getAltura() {
		// TODO Auto-generated method stub
		return getAltura(this.raiz);
	}
	
	private int lengthNodos(NodoArbol<E> r) {
		if(r==null)
			return 0;
		return 1+lengthNodos(r.getIzquierdo())+lengthNodos(r.getDerecho());
	}
	
	public int lengthNodos() {
		return lengthNodos(this.raiz);
	}





	public ListaDinamica<E> search(E item, Comparator<E> comparador ) {
		ListaDinamica<E> resultados = new ListaDinamica<>();
		search(raiz, item, comparador, resultados);
		return resultados;
	}
	private void search(NodoArbol<E> nodo, E item,  Comparator<E> comparador, ListaDinamica<E> resultados) {
		if (nodo == null) return;

		if (comparador.compare(nodo.getItem(), item) == 0) {
			try {
				resultados.add(nodo.getItem());
				//System.out.println("Corrida añadida: " + nodo.getItem());
			} catch (ExcepcionDeListaLlena e) {
				System.out.println("Error al agregar un elemento a la lista de resultados");
			}
		}
		search(nodo.getIzquierdo(), item, comparador, resultados);
		search(nodo.getDerecho(), item, comparador, resultados);
	}

	public ListaDinamica<E> listarCorridas() {
		ListaDinamica<E> lista = new ListaDinamica<>();
		listarCorridasRecursivo(this.raiz, lista);
		return lista;
	}

	private void listarCorridasRecursivo(NodoArbol<E> nodo, ListaDinamica<E> lista) {
		if (nodo == null) return;
		try {
			lista.add(nodo.getItem());
			listarCorridasRecursivo(nodo.getIzquierdo(), lista);
			listarCorridasRecursivo(nodo.getDerecho(), lista);
		} catch (ExcepcionDeListaLlena e) {
			System.out.println("Error: Lista llena. No se pueden agregar más corridas.");
		}
	}





}
