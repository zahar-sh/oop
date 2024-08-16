package sample;

import archiver.Archiver;
import serializer.Serializer;
import transport.Transport;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Objects;

class ArchiverSerializer implements Serializer {
    private final Serializer back;
    private final Archiver archiver;

    public ArchiverSerializer(Serializer back, Archiver archiver) {
        this.back = Objects.requireNonNull(back);
        this.archiver = Objects.requireNonNull(archiver);
    }

    public void save(OutputStream out, Collection<? extends Transport> ts) throws Exception {
        back.save(archiver.compress(out), ts);
    }

    public Collection<? extends Transport> load(InputStream in) throws Exception {
        return back.load(archiver.decompress(in));
    }
}
