package com.iopipe;

import com.iopipe.http.RemoteConnectionFactory;
import java.io.PrintStream;

/**
 * This class is used to initialize instances of {@link IOPipeConfiguration}
 *
 * @since 2017/12/13
 */
public class IOPipeConfigurationBuilder
{
	/**
	 * This lock is used to prevent situations such as mismatched values when
	 * multiple threads are setting parameters or building configurations.
	 */
	protected final Object lock =
		new Object();
	
	/** Is the service enabled? */
	volatile boolean _enabled;
	
	/** The project token. */
	volatile String _token;
	
	/** Debug stream, this is optional. */
	volatile PrintStream _debug;
	
	/** The factory to use for connections. */
	volatile RemoteConnectionFactory _connectionfactory;
	
	/** The timeout window in milliseconds. */
	volatile int _timeoutwindow;
	
	/** Install method. */
	volatile String _installmethod;
	
	/**
	 * This constructs an instance of the configuration settings from the
	 * requested configuration values.
	 *
	 * @return An immutable instance of this configuration.
	 * @throws IllegalArgumentException If a specified configuration setting
	 * is not valid.
	 * @since 2017/12/13
	 */
	public final IOPipeConfiguration build()
		throws IllegalArgumentException
	{
		synchronized (this.lock)
		{
			return new IOPipeConfiguration(this);
		}
	}
	
	/**
	 * Sets the factory to be used to make HTTP connections to the sevrice.
	 *
	 * @param __cf The factory to use for creating new HTTP connections.
	 * @since 2017/12/13
	 */
	public final void setRemoteConnectionFactory(
		RemoteConnectionFactory __cf)
	{
		synchronized (this.lock)
		{
			this._connectionfactory = __cf;
		}
	}
	
	/**
	 * Specifies that debugging should be enabled and that all debugging output
	 * is to be written to the given stream.
	 *
	 * @param __ps The stream to print debugging messages to.
	 * @since 2017/12/13
	 */
	public final void setDebugStream(PrintStream __ps)
	{
		synchronized (this.lock)
		{
			this._debug = __ps;
		}
	}
	
	/**
	 * Sets whether the IOPipe service to to be enabled.
	 *
	 * @param __enabled If {@code true} then the IOPipe service is to be used,
	 * otherwise any requests will NOT use the service.
	 * @since 2017/12/13
	 */
	public final void setEnabled(boolean __enabled)
	{
		synchronized (this.lock)
		{
			this._enabled = __enabled;
		}
	}
	
	/**
	 * Sets the install method.
	 *
	 * @param __im The install method.
	 * @since 2017/12/13
	 */
	public final void setInstallMethod(String __im)
	{
		synchronized (this.lock)
		{
			this._installmethod = __im;
		}
	}
	
	/**
	 * Sets the project token.
	 *
	 * @param __token The token which specifies the project to measure
	 * the statistics for.
	 * @since 2017/12/13
	 */
	public final void setProjectToken(String __token)
	{
		synchronized (this.lock)
		{
			this._token = __token;
		}
	}
	
	/**
	 * Sets the timeout window in milliseconds.
	 *
	 * @param __ms The length of the timeout window, zero disables this
	 * feature.
	 * @throws IllegalArgumentException If the length is negative.
	 * @since 2017/12/13
	 */
	public final void setTimeOutWindow(int __ms)
		throws IllegalArgumentException
	{
		if (__ms < 0)
			throw new IllegalArgumentException("The timeout window " +
				"cannot be negative.");
		
		synchronized (this.lock)
		{
			this._timeoutwindow = __ms;
		}
	}
}

