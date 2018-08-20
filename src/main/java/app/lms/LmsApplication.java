package app.lms;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.reflections.Reflections;

import com.mysql.fabric.xmlrpc.base.Array;

public class LmsApplication {

	public static void run() {
		System.out.println("Scanning using Reflections:");

		Reflections reflector = new Reflections("app");
		for (Class<?> controller : reflector.getTypesAnnotatedWith(ApiVersionControl.class)) {
			Annotation[] antts = controller.getAnnotations();

			for (Annotation antt : antts) {

				if (antt.annotationType().equals(RequestMapping.class)) {
					RequestMapping rmt = (RequestMapping) antt;
					String path = rmt.value()[0];
					
					Annotation newAnnotation = new RequestMapping() {

						@Override
						public Class<? extends Annotation> annotationType() { return rmt.annotationType(); }

						@Override
						public String name() { return rmt.name(); }

						@Override
						public String[] value() { return "v2/" + rmt.value()[0]; }

						@Override
						public String[] path() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public RequestMethod[] method() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String[] params() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String[] headers() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String[] consumes() {
							// TODO Auto-generated method stub
							return null;
						}

						@Override
						public String[] produces() {
							// TODO Auto-generated method stub
							return null;
						}
						
					};

				}

			}
		}

//		for (Class<?> clazz : reflector.getTypesAnnotatedWith(ApiConfigurable.class)) {
//			Field[] fields = clazz.getDeclaredFields();
//			fields[0].get(Class.class);
//			System.out.printf("Found class" + fields.length);
//		}

	}

}
