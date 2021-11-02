package es.curso.modelo;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import es.curso.modelo.entidad.Coche;
import es.curso.modelo.negocio.Rules;
import es.curso.modelo.persistencia.DaoCocheJpaData;

@SpringBootApplication
public class Ej21CochesSpringBootJpaDataApplication implements CommandLineRunner{
	
	
	
	@Autowired
	public DaoCocheJpaData dc;
	
	@Autowired 
	public Coche c;
	
	public static void main(String[] args) {
	
		SpringApplication.run(Ej21CochesSpringBootJpaDataApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);			
		String stringScanner;
		int intScanner;
		boolean init = true;
		Rules rule1 = new Rules();
		
		System.out.println("Inicializando BBDD...");		
		
		System.out.println("Bienvenido, ¿qué operación deseas hacer?");
		do {
			System.out.println("1-Agregar un nuevo coche");
			System.out.println("2-Borrar un coche existente");
			System.out.println("3-Modificar un coche existente");
			System.out.println("4-Mostrar un coche por su id");
			System.out.println("5-Listar todos los coches");
			System.out.println("0-Salir");
			int opcion = sc.nextInt(); 
			switch (opcion) {
			case 1:
				Coche agregacion = new Coche();
				
				System.out.println("Introduce una matricula válida: ");
				stringScanner = sc.next();
				if (rule1.verificarMatricula(agregacion, stringScanner)) {
					agregacion.setMatricula(stringScanner);
				}else break;
				
				System.out.println("Introduce una marca: ");
				stringScanner = sc.next();
				if (rule1.verificarMarcaYModelo(agregacion, stringScanner)) {
					agregacion.setMarca(stringScanner);
				}else break;
				
				System.out.println("Introduce un modelo:");
				stringScanner = sc.next();
				if (rule1.verificarMarcaYModelo(agregacion, stringScanner)) {
					agregacion.setModelo(stringScanner);
				}else break;
				
				
				System.out.println("Introduce los kiómetros:");
				intScanner = sc.nextInt();
				agregacion.setKilometros(intScanner);
				
				dc.save(agregacion);
				System.out.println("El coche se dió de alta!!");
				break;
			
			case 2:
				System.out.println("Introduce el id del coche que deseas eliminar:");
				intScanner = sc.nextInt();
				dc.deleteById(intScanner);
				System.out.println("El coche se dió de baja!!");
				break;
			
			case 3:
				System.out.println("Introduce el id del coche que deseas modificar");
				intScanner = sc.nextInt();
				Coche modificacion = dc.findById(intScanner).get();
				
				System.out.println("Introduce una matricula válida: ");
				stringScanner = sc.next();
				if (rule1.verificarMatricula(modificacion, stringScanner)) {
					modificacion.setMatricula(stringScanner);
				}else break;
				
				System.out.println("Introduce una marca: ");
				stringScanner = sc.next();
				if (rule1.verificarMarcaYModelo(modificacion, stringScanner)) {
					modificacion.setMarca(stringScanner);
				}else break;
				
				System.out.println("Introduce un modelo:");
				stringScanner = sc.next();
				if (rule1.verificarMarcaYModelo(modificacion, stringScanner)) {
					modificacion.setModelo(stringScanner);
				}else break;
				
				System.out.println("Introduce los kiómetros:");
				intScanner = sc.nextInt();
				modificacion.setKilometros(intScanner);
				
				dc.save(modificacion);
				System.out.println("El coche modificó!!");
				break;
				
			case 4:
				System.out.println("Introduce el id del coche que quieres ver: ");
				intScanner = sc.nextInt();
				System.out.println(dc.findById(intScanner));
				break;
				
			case 5:
				System.out.println(dc.findAll());
				break;
			
			case 0:
				 init = false;
				 System.out.println("Hasta la próxima");
				break;
				
			default:
				System.out.println("Introduce una opcion correcta.");
			}
			
		} while (init);
		
	}

}
