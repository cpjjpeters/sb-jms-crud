package be.belfius.sbjmscrud.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tagfile {
	private String completeFile;
//	private List<String> completeFile;
	public Tagfile(String completeFile) {
		super();
		this.completeFile = completeFile;
	}
}
