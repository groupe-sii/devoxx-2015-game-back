package fr.sii.survival.core.domain.action;

import fr.sii.survival.core.domain.board.Cell;
import fr.sii.survival.core.domain.image.Image;

public class AddImage extends ImageActionBase {

	public AddImage(Image image, Cell cell) {
		super(image, cell);
	}

}
