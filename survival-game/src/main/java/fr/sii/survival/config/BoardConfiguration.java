package fr.sii.survival.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.sii.survival.core.domain.Cell;
import fr.sii.survival.core.domain.player.Player;
import fr.sii.survival.core.service.board.BoardService;

@Configuration
public class BoardConfiguration {
	@Bean
	public BoardService boardService() {
		// TODO: use real board service
		return new BoardService() {
			
			@Override
			public Cell move(Player player, Cell cell) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public List<Player> getPlayers(Cell cell) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
