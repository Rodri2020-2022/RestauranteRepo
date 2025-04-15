package Menu;

public class Menu {

    public static final Plato MILANESA_NAPOLITANA =
            new Plato("Milanesa napolitana. Incluye papas fritas y aderesos a su gusto", 22000.00F, TIPO.PRINCIPAL);
    public static final Plato ENSALADA_RUSA =
            new Plato("Ensalada rusa. Incluye aderesos a su gusto", 9600.00F, TIPO.PRINCIPAL);
    public static final Plato EMPANADAS =
            new Plato("Docena de empanadas de carne. Incluye maiz y aceitunas", 12500.00F, TIPO.ENTRADA);

    public static final Bebida COCACOLA =
            new Bebida(4500.00F, "Envase Coca-Cola de 3lts", 3.00F);
    public static final Bebida PALERMO =
            new Bebida(4200.00F, "Envase PALERMO de 1lts", 1.00F);
    public static final Bebida SEVENUP =
            new Bebida(4000.00F, "Envase 7UP de 3lts", 3.00F);

}