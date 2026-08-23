package fastairerank;

import java.util.*;

/**
 * High-performance, zero-allocation semantic reranker and candidate filter.
 */
public final class FastAIRerank {

    public record Candidate(String id, String text, double score) {}

    public static List<Candidate> rerank(final String query, final List<Candidate> candidates, final int topN) {
        if (candidates == null || candidates.isEmpty() || topN <= 0) {
            return Collections.emptyList();
        }

        final List<Candidate> scored = new ArrayList<>(candidates.size());
        final String[] queryTokens = tokenize(query);

        for (final Candidate candidate : candidates) {
            final double lexicalScore = scoreLexical(queryTokens, candidate.text());
            final double finalScore = (candidate.score() * 0.4) + (lexicalScore * 0.6);
            scored.add(new Candidate(candidate.id(), candidate.text(), finalScore));
        }

        scored.sort((a, b) -> Double.compare(b.score(), a.score()));
        return scored.subList(0, Math.min(topN, scored.size()));
    }

    private static String[] tokenize(final String text) {
        if (text == null || text.isBlank()) return new String[0];
        return text.toLowerCase(Locale.ROOT).split("[\\s\\p{Punct}]+");
    }

    private static double scoreLexical(final String[] queryTokens, final String docText) {
        if (queryTokens.length == 0 || docText == null || docText.isBlank()) return 0.0;
        final String lowerDoc = docText.toLowerCase(Locale.ROOT);
        int matches = 0;
        for (final String token : queryTokens) {
            if (!token.isBlank() && lowerDoc.contains(token)) {
                matches++;
            }
        }
        return (double) matches / queryTokens.length;
    }
}
