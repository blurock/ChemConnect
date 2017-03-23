package info.esblurock.reaction.client.panel.data.thermo;

import com.google.gwt.user.client.rpc.AsyncCallback;

import info.esblurock.reaction.data.MatrixOfDoubles;

public class DrawChartEnthalpyCallback implements AsyncCallback<MatrixOfDoubles> {

	DrawThermodynamicsGraph draw;
	
	public DrawChartEnthalpyCallback(DrawThermodynamicsGraph draw) {
		super();
		this.draw = draw;
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(MatrixOfDoubles result) {
		draw.drawMatrix(draw.getTitle(), result);
	}

}
