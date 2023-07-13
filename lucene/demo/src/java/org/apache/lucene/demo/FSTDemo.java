package org.apache.lucene.demo;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.IntsRefBuilder;
import org.apache.lucene.util.fst.FST;
import org.apache.lucene.util.fst.FSTCompiler;
import org.apache.lucene.util.fst.PositiveIntOutputs;
import org.apache.lucene.util.fst.Util;

import java.io.IOException;

/**
 * DESCRIPTION:
 *
 * @author wangmin
 * @since 2023/7/13 17:59
 */
public class FSTDemo {
    public static void main(String[] args) throws IOException {
        String[] inputValues = {"bat", "cat", "deep", "do", "dog", "dogs"};
        long[] outputvalues = {2, 5, 15, 10, 3, 2};

        PositiveIntOutputs outputs = PositiveIntOutputs.getSingleton();
        FSTCompiler.Builder<Long> builder = new FSTCompiler.Builder<>(FST.INPUT_TYPE.BYTE1, outputs);
        FSTCompiler<Long> build = builder.build();
        IntsRefBuilder intsRefBuilder = new IntsRefBuilder();
        for (int i = 0; i < inputValues.length; i ++) {
            BytesRef bytesRef = new BytesRef(inputValues[i]);
            build.add(Util.toIntsRef(bytesRef, intsRefBuilder), outputvalues[i]);
        }

        FST<Long> fst = build.compile();
        BytesRef bytesRef = new BytesRef(inputValues[3]);
        Long aLong = Util.get(fst, Util.toIntsRef(bytesRef, intsRefBuilder));

        System.out.println(aLong);
    }
}
