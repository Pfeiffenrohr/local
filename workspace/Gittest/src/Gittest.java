import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class Gittest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		// Creation of a temp folder that will contain the Git repository
		File workingDirectory = File.createTempFile("nuxeo-git-test", "");
		workingDirectory.delete();
		workingDirectory.mkdirs();

		// Create a Repository object
		Repository repo = FileRepositoryBuilder.create(new File(workingDirectory, ".git"));
		repo.create();
		Git git = new Git(repo);

		// Create a new file and add it to the index
		File newFile = new File(workingDirectory, "myNewFile");
		newFile.createNewFile();
		git.add().addFilepattern("myNewFile").call();

		// Now, we do the commit with a message
		RevCommit rev = git.commit().setAuthor("gildas", "gildas@example.com").setMessage("My first commit").call();

	
	}
		catch (Exception e) {
		System.out.println("Exception");
	}

}
}
