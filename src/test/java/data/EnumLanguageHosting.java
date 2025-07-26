package data;

public enum EnumLanguageHosting {

    NE("Nederlands", "Dedicated Servers"),
    ES("Español","Servidores dedicados" );

    public final String name ;
    public final String button;

    EnumLanguageHosting(String name, String button) {
        this.name = name;
        this.button = button;
    }

}
