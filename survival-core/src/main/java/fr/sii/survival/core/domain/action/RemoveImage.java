package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class RemoveImage extends ImageActionBase {
	
	public RemoveImage(Image image, Cell cell) {
		super(image, cell);
	}

}
