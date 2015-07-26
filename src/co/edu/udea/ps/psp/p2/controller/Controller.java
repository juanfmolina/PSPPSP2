package co.edu.udea.ps.psp.p2.controller;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import co.edu.udea.ps.psp.p2.countermodel.CounterModel;
import co.edu.udea.ps.psp.p2.exception.CounterModelException;
import co.edu.udea.ps.psp.p2.filemanager.FileManager;
import co.edu.udea.ps.psp.p2.model.ClassComponent;
import co.edu.udea.ps.psp.p2.model.MethodComponent;

public class Controller {
	private FileManager fileManager;
	private CounterModel counterModel;

	public Controller() {
		fileManager = new FileManager();
		counterModel = new CounterModel();
	}

	public LinkedList<ClassComponent> getCountedClasses(String rootDirectory) throws CounterModelException {
		LinkedList<ClassComponent> allClasses = new LinkedList<>();
		try {
			List<String> contentOfFiles = fileManager.getAllLinesOfDirectory(rootDirectory);
			if (contentOfFiles.size()==0) {
				throw new CounterModelException("En el directorio seleccionado no hay clases java");
			}
			for (String eachClass : contentOfFiles) {
				ClassComponent classComponent = counterModel.returnClass(eachClass);
				classComponent.setMethods(counterModel.returnMethods(eachClass));
				if (classComponent.getMethods().size() == 0) {
					classComponent.setNumberOfLinesOfCode(counterModel.countLinesOfCode(eachClass));
					
				} else

				if (classComponent.getMethods().size() == 1) {
					classComponent.setNumberOfLinesOfCode(counterModel.countLinesOfCode(eachClass));
					MethodComponent component = classComponent.getMethods().get(0);
					component.setNumberOfLinesOfCode(counterModel
							.countLinesOfCode(eachClass.substring(component.getStartsIn(), eachClass.length())));
				} else {
					classComponent.setNumberOfLinesOfCode(counterModel.countLinesOfCode(eachClass));
					for (int i = 0; i < classComponent.getMethods().size(); i++) {
						if (i+1 == classComponent.getMethods().size()) {
							MethodComponent component = classComponent.getMethods().get(i);
							component.setNumberOfLinesOfCode(counterModel
									.countLinesOfCode(eachClass.substring(component.getStartsIn(), eachClass.length())));
						}else{
							MethodComponent component1= classComponent.getMethods().get(i);
							MethodComponent component2= classComponent.getMethods().get(i+1);
							component1.setNumberOfLinesOfCode(counterModel
									.countLinesOfCode(eachClass.substring(component1.getEndsIn(),component2.getStartsIn())));
						}
						
					}
				}
				allClasses.add(classComponent);
			}
		} catch (FileNotFoundException e) {
			throw new CounterModelException("No se puede encontrar el archivo o la ruta especificada");
		}
		return allClasses;
	}

}
