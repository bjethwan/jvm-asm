package com.bjethwan;

interface Element {
	public void accept( Visitor v ); // first dispatch
}

class ElementA implements Element {
	public void   accept( Visitor v ) {
		v.visit( this );
	} 
	public String a() {
		return "A ";
	}
}

class ElementB implements Element {
	public void   accept( Visitor v ) {
		v.visit( this );
	}
	public String b() {
		return "B ";
	}
}

class ElementC implements Element {
	public void   accept( Visitor v ) {
		v.visit( this );
	}
	public String c() {
		return "C "; 
	}
}


// 2. Create a "visitor" base class with a visit() method for every "element" type
interface Visitor {
	public void visit( ElementA e ); // second dispatch
	public void visit( ElementB e );
	public void visit( ElementC e );
}

// 3. Create a "visitor" derived class for each "operation" to perform on "elements"
class UpVisitor implements Visitor {                   
	public void visit( ElementA e ) {
		System.out.println( "do Up on " + e.a() );
	}
	public void visit( ElementB e ) {
		System.out.println( "do Up on " + e.b() );
	}
	public void visit( ElementC e ) {
		System.out.println( "do Up on " + e.c() );
	}
}

class DownVisitor implements Visitor {
	public void visit( ElementA e ) {
		System.out.println( "do Down on " + e.a() );
	}
	public void visit( ElementB e ) {
		System.out.println( "do Down on " + e.b() );
	}
	public void visit( ElementC e ) {
		System.out.println( "do Down on " + e.c() );
	}
}

class CompositeObj1{
	public static Element[] list = { new ElementA(), new ElementB(), new ElementA(), new ElementC(), new ElementC() };
}

class CompositeObj2{
	public static Element[] list = { new ElementA(), new ElementB(), new ElementA(), new ElementB(), new ElementA(), new ElementB() };
}



public class VisitorDemo {

	// 4. Client creates "visitor" objects and passes each to accept() calls
	public static void main( String[] args ) {
		UpVisitor    up   = new UpVisitor();
		DownVisitor  down = new DownVisitor();
		
		
		System.out.println("Processing CompositeObj1");
		for (int i=0; i < CompositeObj1.list.length; i++) {
			CompositeObj1.list[i].accept( up );
		}
		for (int i=0; i < CompositeObj1.list.length; i++) {
			CompositeObj1.list[i].accept( down );
		}
		
		
		
		System.out.println("\nProcessing CompositeObj2");
		for (int i=0; i < CompositeObj2.list.length; i++) {
			CompositeObj2.list[i].accept( up );
		}
		for (int i=0; i < CompositeObj2.list.length; i++) {
			CompositeObj2.list[i].accept( down );
		}
	}
}


