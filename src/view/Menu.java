package view;

import view.types.InfoType;

import java.util.Scanner;
import java.util.Set;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Character> HOME_OPTIONS = Set.of('L', 'M', 'P', 'C', 'D', 'O', 'F', 'S', 'Q');

    public static char home() {
        char selected;
        while (true) {
            PrintComponent.home();
            System.out.print("\nAção selecionada >> ");
            selected = scanner.next().toUpperCase().charAt(0);

            if (HOME_OPTIONS.contains(selected)) {
                break;
            }

            PrintComponent.info(InfoType.ERROR, "Ação indisponível, selecione outra no menu");
        }

        return selected;
    }
}
