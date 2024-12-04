package de.leowgc.wgccore.util;


import java.util.function.Consumer;
import java.util.function.LongSupplier;

public class Benchmarker {
    public static final Benchmarker NANOS = new Benchmarker(System::nanoTime);
    public static final Benchmarker MILLIS = new Benchmarker(System::currentTimeMillis);

    private final LongSupplier timeSource;

    private long startTime;
    private Consumer<Long> endAction = null;

    private Benchmarker ( LongSupplier pTimeSource ) {
        this.timeSource = pTimeSource;
    }

    /**
     * Starts the benchmark.
     * @throws IllegalStateException if start() is called without calling end() first if was called before.
     * @since 1.0.0 - 1.21.4
     */
    public void start () {
        if (this.startTime != 0) throw new IllegalStateException("end() must be called before start() can be invoked again.");
        this.startTime = this.timeSource.getAsLong();
    }

    /**
     * Starts the benchmark.
     * @throws IllegalStateException if start() is called without calling end() first if was called.
     * @param pEndAction The executed action when ending benchmark
     * @since 1.0.0 - 1.21.4
     */
    public void start ( Consumer<Long> pEndAction ) {
        this.start();
        this.endAction = pEndAction;
    }

    /**
     * End the benchmark and returns the elapsed time.
     * @return The elapsed time since start() was called.
     * @throws IllegalStateException if end() is called without calling start() first.
     * @since 1.0.0 - 1.21.4
     */
    public long end () {
        if (this.startTime == 0)
            throw new IllegalStateException("start() must be called before end()");

        long duration = this.timeSource.getAsLong() - this.startTime;
        if ( this.endAction != null ) this.endAction.accept(duration);

        // Reset state for reuse
        this.startTime = 0;
        this.endAction = null;

        return duration;
    }

    /**
     * Formats a duration for readaibility
     * @param pDuration The duration to format
     * @param pNanos True if the duration is in nanoseconds; false if in milliseconds.
     * @return A formatted string representing the duration.
     * @since 1.0.0 - 1.21.4
     */
    public static String formatDuration ( long pDuration, boolean pNanos ) {
        if ( pNanos ) return String.format("%.3f ms", pDuration / 1_000_000.0);
        return pDuration + " ms";
    }

}
