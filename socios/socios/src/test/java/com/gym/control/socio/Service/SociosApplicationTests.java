package com.gym.control.socio.Service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gym.control.socio.dto.SocioDTO;
import com.gym.control.socio.repository.SocioRepository;
import com.gym.control.socio.service.SocioService;
import com.gym.control.socio.model.Socio;

import net.datafaker.Faker;

@ExtendWith(MockitoExtension.class)
class SociosApplicationTests {

   @Mock
   private SocioRepository socioRepository; // Simulamos el acceso a la base de datos
  
   @InjectMocks
   private SocioService socioService; // Inyectamos el Mock anterior dentro del servicio real
   
   private Faker faker = new Faker(); // Nuestro generador de datos del gimnasio
   
   @BeforeEach
   void setUp() {
       // Inicializa los componentes de simulación antes de ejecutar cada prueba
       MockitoAnnotations.openMocks(this);
   }

   @Test
   void testBuscarPorId_Exitoso() {
       // GIVEN: Dado un escenario inicial en el gimnasio
       Integer idSimulado = 42;
       String nombreAleatorio = faker.name().firstName(); // Genera nombres aleatorios como Juan, Carlos o Valentina
       
       Socio socioFalso = new Socio();
       socioFalso.setId(idSimulado);
       socioFalso.setNombre(nombreAleatorio);
       
       // Nota: Si tu modelo Socio tiene campos adicionales (como apellido, edad o correo), 
       // puedes agregarlos aquí simulando los midiclorianos del profe, por ejemplo:
       // socioFalso.setEdad(faker.number().numberBetween(18, 70));

       // Entrenamos al Mock: Cuando el repositorio busque este ID, responderá con nuestro socioFalso
       when(socioRepository.findById(idSimulado)).thenReturn(Optional.of(socioFalso));
       
       // WHEN: Cuando ejecutamos la acción del servicio que queremos evaluar
       SocioDTO resultado = socioService.buscarPorId(idSimulado);
       
       // THEN: Entonces validamos que las compuertas de datos funcionen de forma idónea
       assertNotNull(resultado, "El DTO resultante no debería ser nulo");
       assertEquals(nombreAleatorio, resultado.getNombre(), "El nombre transformado al DTO debe coincidir con el de la DB");
       
       // Verificamos que el servicio realmente haya consultado al repositorio exactamente 1 vez
       verify(socioRepository, times(1)).findById(idSimulado);
   }

}