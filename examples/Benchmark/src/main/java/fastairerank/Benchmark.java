package fastairerank;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * JMH Microbenchmark — FastAIRerank semantic relevance scoring and candidate sorting.
 */
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgs = {"-server", "-XX:+UseG1GC", "-Xms256m", "-Xmx256m"})
public class Benchmark {

    private List<FastAIRerank.Candidate> candidates;

    @Setup(Level.Trial)
    public void setup() {
        this.candidates = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            this.candidates.add(new FastAIRerank.Candidate(
                "doc_" + i,
                "Candidate document text chunk " + i + " describing high throughput Java JIT optimization and low latency.",
                0.5 + (i * 0.005)
            ));
        }
    }

    @Benchmark
    public List<FastAIRerank.Candidate> benchmarkReranking50Candidates() {
        return FastAIRerank.rerank("Java JIT optimization low latency", this.candidates, 5);
    }
}
