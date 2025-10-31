package view;

import util.CliUtils;
import view.types.InfoType;

class PrintComponent {
    public static void info(InfoType type, String message) {
        String border = "═".repeat(message.length() + 28);

        CliUtils.clear();
        System.out.println(type.getColor());
        System.out.println("╔" + border + "╗");
        System.out.println("║              " + message.toUpperCase() + "              ║");
        System.out.println("╚" + border + "╝");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void home() {
        CliUtils.clear();
        System.out.println("╔═════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         JAVA TODO CLI - MENU                        ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ [L] Listar tarefas       [C] Criar tarefa      [F] Filtrar          ║");
        System.out.println("║ [M] Marcar concluída     [D] Excluir tarefa    [S] Buscar           ║");
        System.out.println("║ [P] Próxima página       [O] Ordem/Sort        [Q] Sair             ║");
        System.out.println("╠═════════════════════════════════════════════════════════════════════╣");
        System.out.println("║ Atalho: Pressione a tecla entre colchetes e ENTER.                  ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════╝");
    }
}
