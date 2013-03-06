import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;

/**
 * Unit test for mapper
 * @author hduser
 *
 */
public class IndexMapperTest {

	
	@Test
	public void testMapper() throws IOException, InterruptedException {
		IndexMapper mapper = new IndexMapper();
		Text value = new Text("Bonjour ubuntu mercredi");
		IndexMapper.Context context = mock(IndexMapper.Context.class);
		
		mapper.map(null, value, context);
		
		verify(context).write(new Text("bonjour,null"),null);
	}
}
