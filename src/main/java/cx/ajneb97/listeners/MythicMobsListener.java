package cx.ajneb97.listeners;

import cx.ajneb97.Codex;
import cx.ajneb97.model.CategoriaCodex;
import cx.ajneb97.model.EntradaCodex;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;

public class MythicMobsListener implements Listener {

    private Codex plugin;

    public MythicMobsListener(Codex plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void alMatarMythicMob(MythicMobDeathEvent event) {
        LivingEntity killer = event.getKiller();
        if (killer != null && killer instanceof Player) {
            final Player jugador = (Player) killer;
            String type = event.getMob().getMobType();
            ArrayList<CategoriaCodex> categorias = plugin.getCategoriasManager().getCategorias();
            for (final CategoriaCodex categoria : categorias) {
                List<EntradaCodex> entradas = categoria.getEntradas();
                for (final EntradaCodex entrada : entradas) {
                    if (entrada.getDiscoveredOnMobKillList().stream().anyMatch(opcionesMobKill -> opcionesMobKill.getMythicMobsId() != null && opcionesMobKill.getMythicMobsId().equals(type))) {
                        plugin.getJugadorDataManager().agregarEntrada(jugador, categoria.getPath(), entrada.getId(), agrega -> {
                            if (agrega) {
                                plugin.getCodexManager().desbloquearEntrada(jugador, categoria, entrada);
                            }
                        });
                        return;
                    }
                }
            }
        }
    }
}
